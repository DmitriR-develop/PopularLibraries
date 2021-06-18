package com.example.popularlibraries.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitHubRepo(
    @Expose val id: String? = null,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null
) : Parcelable