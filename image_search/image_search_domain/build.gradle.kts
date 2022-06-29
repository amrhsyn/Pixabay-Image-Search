apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))

    "implementation"(Room.roomPaging)
    "implementation"(Paging.paging)

}