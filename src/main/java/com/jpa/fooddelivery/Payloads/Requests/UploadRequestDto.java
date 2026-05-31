package com.jpa.fooddelivery.Payloads.Requests;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadRequestDto {

    private String folder;

    @Builder.Default
    private String resourceType = "auto";

    @Builder.Default
    private boolean overwrite = false;
}
