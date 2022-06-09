package com.atzzazz.store.controller.ex;

/**
 * アップロードするファイルのステータスが間違っている場合の异常
 */
public class FileStateException extends FileUploadException{
    public FileStateException() {
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    public FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
