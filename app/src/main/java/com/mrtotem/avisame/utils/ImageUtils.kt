package com.mrtotem.avisame.utils

import android.graphics.Bitmap
import android.net.Uri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.backends.pipeline.PipelineDraweeController
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.BasePostprocessor
import com.facebook.imagepipeline.request.ImageRequestBuilder


/**
 * Created by Octavio on 29/01/2018.
 */
class ImageUtils {

    public fun processImage(uri: String?, width: Int, height: Int): PipelineDraweeController {
        var uri = uri
        if (uri == null) {
            uri = ""
        }
        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(ResizeOptions(width, height))
                .build()
        return Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build() as PipelineDraweeController
    }

    public fun processImageWithAlpha(uri: String, width: Int, height: Int): PipelineDraweeController {

        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri))
                .setResizeOptions(ResizeOptions(width, height))
                .setPostprocessor(object : BasePostprocessor() {
                    override fun process(bitmap: Bitmap?) {
                        super.process(bitmap)
                        bitmap!!.density = 20
                    }
                })
                .build()
        return Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build() as PipelineDraweeController
    }
}