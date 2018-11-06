package red.sukun1899.wanko

import org.gradle.api.Plugin
import org.gradle.api.Project
import red.sukun1899.wanko.task.WankoLoadTask

/**
 * @author su-kun1899
 */
class WankoPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        def extension = project.extensions.create('wanko', WankoExtension, project)
        project.tasks.create('wankoLoad', WankoLoadTask) {
            url = extension.url
            user = extension.user
            password = extension.password
            driverClassName = extension.driverClassName
        }
    }
}
