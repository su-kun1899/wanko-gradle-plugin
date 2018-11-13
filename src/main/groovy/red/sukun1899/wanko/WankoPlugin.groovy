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
        project.extensions.create('wanko', WankoExtension)
        project.tasks.create('wankoLoad', WankoLoadTask)
    }
}
