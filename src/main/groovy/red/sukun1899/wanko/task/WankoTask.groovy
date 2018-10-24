package red.sukun1899.wanko.task

import groovy.sql.Sql
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author su-kun1899
 */
class WankoTask extends DefaultTask {
    @TaskAction
    def loadData() {
        println("Not implemented") // TODO
        println("hoge") // TODO

        // TODO オプションとして受け取るようにする
        def sql = Sql.newInstance(
                "jdbc:postgresql://localhost:25432/gengar-sayque",
                "gengar",
                "gengar",
                "org.postgresql.Driver"
        )
    }
}
