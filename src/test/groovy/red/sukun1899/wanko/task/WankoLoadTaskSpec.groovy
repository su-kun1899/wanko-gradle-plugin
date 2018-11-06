package red.sukun1899.wanko.task


import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * @author su-kun1899
 */
class WankoLoadTaskSpec extends Specification {
    WankoLoadTask sut

    def setup() {
        def project = ProjectBuilder.builder().build()
        def task = project.task('wankoLoad', type: WankoLoadTask)
        sut = task as WankoLoadTask
    }

    def "load data"() {
        when:
        sut.loadData()

        then:
        sut instanceof WankoLoadTask
    }
}
