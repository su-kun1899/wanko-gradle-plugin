package red.sukun1899.wanko.config


import red.sukun1899.wanko.WankoExtension

/**
 * @author su-kun1899
 */
class ExtensionConfig implements WankoConfig {
    WankoExtension extension

    @Override
    String url() {
        return extension.url
    }

    @Override
    String user() {
        return extension.user
    }

    @Override
    String password() {
        return extension.password
    }

    @Override
    String driverClassName() {
        return extension.driverClassName
    }

    @Override
    String sqlDir() {
        return extension.sqlDir
    }
}
