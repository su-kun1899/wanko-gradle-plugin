package red.sukun1899.wanko.task

import groovy.sql.Sql
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import red.sukun1899.wanko.WankoExtension
import red.sukun1899.wanko.config.ExtensionConfig
import red.sukun1899.wanko.config.WankoConfig

/**
 * @author su-kun1899
 */
class WankoLoadTask extends DefaultTask {
    WankoConfig config

    WankoLoadTask() {
        def extension = project.extensions.findByName("wanko")
        if (extension == null) {
            return
        }
        this.config = new ExtensionConfig(extension: extension as WankoExtension)
    }

    @TaskAction
    def loadData() {
        // TODO 削除
        println("url=${this.config.url()}")
        println("user=${this.config.user()}")
        println("password=${this.config.password()}")
        println("driverClassName=${this.config.driverClassName()}")
        println("sqlDir=${this.config.sqlDir()}")

        // For load driver class
        // https://discuss.gradle.org/t/jdbc-driver-class-cannot-be-loaded-with-gradle-2-0-but-worked-with-1-12/2277/6
        def loader = Sql.classLoader
        project.buildscript.configurations.classpath.each { File file ->
            loader.addURL(file.toURI().toURL())
        }

        def sql = Sql.newInstance(
                this.config.url(),
                this.config.user(),
                this.config.password(),
                this.config.driverClassName(),
        )

        def result = sql.firstRow("SELECT * FROM pg_catalog.pg_tables")

        // TODO 拡張子sqlでfilter
        // TODO 名前でsort
        // TODO filetreeを使えば階層ディレクトリも対応可能？
        def sqlFiles = project.fileTree(this.config.sqlDir()).collect { it as File }.sort()
        sqlFiles.each { logger.info it.name }
//        project.file('sql').listFiles().collect { it.name }
    }
}
