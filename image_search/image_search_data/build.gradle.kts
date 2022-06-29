apply {
    from("$rootDir/base-module.gradle")
}

dependencies {

    "implementation"(project(Modules.imageSearchDomain))
    "implementation"(project(Modules.core))

    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.gsonConvertor)

    "implementation"(Room.roomRuntime)
    "annotationProcessor"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomPaging)


}