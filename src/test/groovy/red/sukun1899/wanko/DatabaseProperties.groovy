package red.sukun1899.wanko

/**
 * @author su-kun1899
 */
class DatabaseProperties {
    String url
    String user
    String password
    String driverClassName

    DatabaseProperties() {
        def properties = new Properties()
        this.getClass().getResource('/database.properties').withInputStream {
            properties.load it
        }
        this.url = properties.url
        this.user = properties.user
        this.password = properties.password
        this.driverClassName = properties.driverClassName
    }
}
