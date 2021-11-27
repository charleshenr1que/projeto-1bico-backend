package com.charleshenrique.project1bico.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSavingSystem {

	public String basePath = "/home/charles/Documentos/img";

	private Path root = null;

	private Path getPath() {
		if (root == null) {
			root = Paths.get(basePath);
		}
		return root;
	}

	public String save(MultipartFile file, String fileName) {
		getPath();

		String newFileName = fileName + getFileExtension(file.getOriginalFilename());
		File fileExist = new File(basePath.concat("/" + newFileName));

		if (fileExist.exists()) {
			System.out.println("Existe foto");
			fileExist.delete();
		}

		try {
			if (!Files.exists(root)) {
				Files.createDirectories(root);
			}
			Files.copy(file.getInputStream(), this.root.resolve(newFileName));
			return newFileName;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar imagem no sistema: " + e.getMessage());
		}
	}

	static String getFileExtension(String filename) {
		if (filename.contains("."))
			return filename.substring(filename.lastIndexOf("."));
		else
			return "";
	}

	public String readImage(String imageName) {
		getPath();
		String image = null;
		try {
			File file = new File(root + "/".concat(imageName));
			byte[] contents = Files.readAllBytes(file.toPath());
			image = Base64.getEncoder().encodeToString(contents);

		} catch (Exception e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
		return image;
	}

}
