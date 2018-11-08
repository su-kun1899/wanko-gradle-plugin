package red.sukun1899.wanko.task

import groovy.sql.Sql
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskAction

/**
 * @author su-kun1899
 */
class WankoLoadTask extends DefaultTask {
    Property<String> url = project.objects.property(String)
    Property<String> user = project.objects.property(String)
    Property<String> password = project.objects.property(String)
    Property<String> driverClassName = project.objects.property(String)

    @TaskAction
    def loadData() {
        println("url=${url.get()}")
        println("user=${user.get()}")
        println("password=${password.get()}")
        println("driverClassName=${driverClassName.get()}")

        // TODO SQLの実行
        println("Not implemented") // TODO
        println("Hello Testkit2") // TODO
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:25432/postgres",
                "postgres",
                "postgres",
                "org.postgresql.Driver"
        )
    }
}
