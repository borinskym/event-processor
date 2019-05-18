package ct.com.bigpanda.event.processor

import com.bigpanda.event.proccesor.Application
import groovyx.net.http.RESTClient

class AppDriver {

    RESTClient client = new RESTClient('http://localhost:8080')
    def receivedGreeting

    def start() {
        Application.main()
    }

    def isGreeting() {
        def response = client.get(path: '/v1/greeting')
        assert response.status == 200
        receivedGreeting = response.data
    }

    void retrievedGreetingIs(String greeting) {
        assert this.receivedGreeting.greeting == greeting
    }

    def stats() {
        def response = client.get(path: '/v1/stats')
        assert response.status == 200
        return response
    }
}
