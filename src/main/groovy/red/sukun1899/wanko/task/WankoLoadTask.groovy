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

        // For load driver class
        // https://discuss.gradle.org/t/jdbc-driver-class-cannot-be-loaded-with-gradle-2-0-but-worked-with-1-12/2277/6
        def loader = Sql.classLoader
        project.buildscript.configurations.classpath.each { File file ->
            loader.addURL(file.toURI().toURL())
        }

        def sql = Sql.newInstance(
                url.get(),
                user.get(),
                password.get(),
                driverClassName.get(),
        )

        def result = sql.firstRow("SELECT * FROM pg_catalog.pg_tables")

        // TODO 拡張子sqlでfilter
        // TODO 名前でsort
        // TODO filetreeを使えば階層ディレクトリも対応可能？
        // project.fileTree('.').collect { it as File }.sort()
        def fileNames = project.file('sql').listFiles().collect { it.name }
        println(fileNames)
    }
}
