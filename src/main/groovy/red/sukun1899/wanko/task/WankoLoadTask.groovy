package red.sukun1899.wanko.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import red.sukun1899.wanko.WankoExtension

/**
 * @author su-kun1899
 */
class WankoLoadTask extends DefaultTask {
    String url
    String user
    String password
    String driverClassName

    WankoLoadTask() {
        def extension = project.extensions.findByName("wanko") as WankoExtension
        if (extension == null) {
            return
        }
        this.url = extension.url
        this.user = extension.user
        this.password = extension.password
        this.driverClassName = extension.driverClassName
    }

    @TaskAction
    def loadData() {
        println("url=$url")
        println("user=$user")
        println("password=$password")
        println("driverClassName=$driverClassName")

        // TODO SQLの実行
        println("Not implemented") // TODO
//        def sql = Sql.newInstance(
//                "jdbc:postgresql://localhost:25432/gengar-sayque",
//                "gengar",
//                "gengar",
//                "org.postgresql.Driver"
//        )
    }
}
