package red.sukun1899.wanko.task

import org.gradle.api.Project
import org.gradle.api.internal.provider.DefaultPropertyState
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Ignore
import spock.lang.Specification
/**
 * @author su-kun1899
 */
class WankoLoadTaskSpec extends Specification {
    WankoLoadTask sut
    Project project

    def setup() {
        project = ProjectBuilder.builder().build()
        def task = project.task('wankoLoad', type: WankoLoadTask)
        sut = task as WankoLoadTask
        sut.url = new DefaultPropertyState<String>(String)
        sut.user = new DefaultPropertyState<String>(String)
        sut.password = new DefaultPropertyState<String>(String)
        sut.driverClassName = new DefaultPropertyState<String>(String)
    }

    @Ignore
    def "load data"() {
        given:
        sut.url.set "postgres"
        sut.user.set "postgres"
        sut.password.set "postgres"
        sut.driverClassName.set "org.postgresql.Driver"

        when:
        sut.loadData()

        then:
        sut instanceof WankoLoadTask
    }
}
