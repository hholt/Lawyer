package com.wl.lawyer.mvp.model.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TencentUserSignatureBean(
    @SerializedName("signature")val sig: String,
    val nickname: String) : Serializable
/*
* "signature": "eJyrVgrxCdYrSy1SslIy0jNQ0gHzM1NS80oy0zIhwuZQ0eKU7MSCgswUJStDEwMDYyNDcxNDiExqRUFmUSpQ3NTU1MjAwAAiWpKZCxazsLAwNrEwN4aakpkONNQny7HSojiiMiVG3zzVuCTIKL-cwsUpP7TE0MLd37nEN9I0IkY-zyw9SDsnKdlWqRYAjTEwGw__",
  "nickname": "18627794119"
*
* */