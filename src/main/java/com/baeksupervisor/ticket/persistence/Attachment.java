package com.baeksupervisor.ticket.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Base64;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/13
 */
@Getter
@NoArgsConstructor
@Entity
public class Attachment implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String mimeType;

    @Setter
    private String originalFilename;

    @Setter
    private Long fileSize;

    @Setter
    @Lob
    private byte[] blob;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Setter
    @Transient
    private MultipartFile multipartFile;

    public String base64Image() {
        return Base64.getEncoder().encodeToString(this.blob);
    }
}
