package com.diego.findeciclo.service.cloudinary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryService {

    private Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<String, String>();

    public CloudinaryService() {

        valuesMap.put("cloud_name", "diegogmino");
        valuesMap.put("api_key", "523944233851591");
        valuesMap.put("api_secret", "9v6bMBdoX99-5FYs8qtDZQwkk4M");
        cloudinary = new Cloudinary(valuesMap);

    }

    public Map upload(MultipartFile multipartFile) throws IOException {

        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        file.delete();
        return result;

    }

    public Map delete(String id) throws IOException {

        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;

    }

    private File convert(MultipartFile multipartFile) throws IOException {

        InputStream initialStream = multipartFile.getInputStream();
        byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);

        File file = new File("src/main/resources/targetFile.tmp");

        try (OutputStream outStream = new FileOutputStream(file)) {
            outStream.write(buffer);
        }

        return file;

    }

}
