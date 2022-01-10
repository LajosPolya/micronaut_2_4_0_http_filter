package com.lajospolya.app

import io.micronaut.core.async.publisher.Publishers
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Filter
import io.micronaut.http.annotation.Get
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import org.reactivestreams.Publisher

@Controller
class HomeController {

    @Get("/home")
    fun getHome(): String {
        return "This should not be in the response body"
    }
}

data class FilterResponseDto(
    val integerField: Int,
    val stringField: String,
)

@Filter("/home")
class HttpFilter: HttpServerFilter {
    override fun doFilter(request: HttpRequest<*>?, chain: ServerFilterChain?): Publisher<MutableHttpResponse<*>> {
        return Publishers.just(HttpResponse.ok(FilterResponseDto(12345, "Returned From Filter")))
    }

}