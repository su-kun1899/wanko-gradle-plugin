package red.sukun1899.wanko

import org.gradle.api.Plugin
import org.gradle.api.Project
import red.sukun1899.wanko.task.WankoTask

/**
 * @author su-kun1899
 */
class WankoPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.create('wanko', WankoTask)
    }
}
