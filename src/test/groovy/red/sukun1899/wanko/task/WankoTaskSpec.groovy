package red.sukun1899.wanko.task

import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoTaskSpec extends Specification {
    def "hello"() {
        when:
        new WankoTask().loadData()

        then:
        true
    }
}
