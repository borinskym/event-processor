package ct.com.bigpanda.event.processor

import spock.lang.Shared
import spock.lang.Specification

class EventProcessorSpec extends Specification {

    @Shared
    AppDriver app = new AppDriver()

    def setupSpec() {
        app.start()
    }

    def "should gather statistic for stream events" (){
        when:
        def response = app.stats()
        then:
        assert response.data ==
                ['eventTypeCount': ['baz': 1],
                 'eventDataCount': ['amet': 1]]
    }

}