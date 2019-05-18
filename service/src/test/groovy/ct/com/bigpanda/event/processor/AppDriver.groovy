package ct.com.bigpanda.event.processor

import com.bigpanda.event.proccesor.Application
import groovyx.net.http.RESTClient

class AppDriver {

    RESTClient client = new RESTClient('http://localhost:8080')

    def start() {
        Application.main()
    }

    def stats() {
        def response = client.get(path: '/v1/stats')
        assert response.status == 200
        return response
    }
}
