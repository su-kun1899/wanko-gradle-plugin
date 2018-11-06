package red.sukun1899.wanko

import org.gradle.api.Project
import org.gradle.api.provider.Property

/**
 * @author su-kun1899
 */
class WankoExtension {
    final Property<String> url
    final Property<String> user
    final Property<String> password
    final Property<String> driverClassName

    WankoExtension(Project project) {
        this.url = project.objects.property(String)
        this.user = project.objects.property(String)
        this.password = project.objects.property(String)
        this.driverClassName = project.objects.property(String)
    }
}
