package com.baeksupervisor.ticket.model;

import com.baeksupervisor.ticket.persistence.Attachment;
import lombok.Getter;

import java.io.Serializable;
import java.util.Base64;

/**
 * Created by Seunghyun.Baek
 * Since 2020/03/13
 */
@Getter
public class TicketAttachmentView implements Serializable {

    @Getter
    private String filename;

    @Getter
    private Long fileSize;

    @Getter
    private String mimeType;

    private byte[] file;

    public static TicketAttachmentView getInstance(Attachment attachment) {
        TicketAttachmentView view = new TicketAttachmentView();
        view.filename = attachment.getOriginalFilename();
        view.fileSize = attachment.getFileSize();
        view.mimeType = attachment.getMimeType();
        view.file = attachment.getBlob();
        return view;
    }

    public String base64Image() {
        return Base64.getEncoder().encodeToString(this.file);
    }
}
