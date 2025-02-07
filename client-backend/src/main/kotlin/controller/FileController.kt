package org.napbad.clientbackend.controller


import lombok.extern.slf4j.Slf4j
import org.babyfish.jimmer.client.meta.Api
import org.napbad.clientbackend.config.FileSaveProperties
import org.springframework.scheduling.annotation.Async
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.concurrent.CompletableFuture

@Api
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/files")
class FileController(private val fileSaveProperties: FileSaveProperties) {

    private val fileStorageLocation: Path = Paths.get(fileSaveProperties.uploadDir)
        .toAbsolutePath().normalize()

    init {
        try {
            Files.createDirectories(fileStorageLocation)
        } catch (ex: Exception) {
            throw RuntimeException("Could not create the directory where the uploaded files will be stored.", ex)
        }
    }

    @Api
    @Async
    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): CompletableFuture<String> {
        try {
            val fileName = file.originalFilename ?: throw RuntimeException("File name is null")
            val targetLocation = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            return CompletableFuture.completedFuture("File uploaded successfully: $fileName")
        } catch (ex: Exception) {

        }
        return CompletableFuture.completedFuture("File upload failed: $file")
    }

//    @Api
//    @GetMapping("/download/{fileName:.+}")
//    fun downloadFile(@PathVariable fileName: String) {
//        try {
//            val file = fileStorageLocation.resolve(fileName).normalize()
//            if (!file.startsWith(fileStorageLocation)) {
//                throw RuntimeException("Cannot read file outside current directory.")
//            }
//
//            val resource = UrlResource(file.toUri())
//            if (resource.exists() && resource.isReadable) {
//                return
//            } else {
//                throw RuntimeException("Could not read file: $fileName")
//            }
//        } catch (ex: Exception) {
//            return
//        }
//    }
}
