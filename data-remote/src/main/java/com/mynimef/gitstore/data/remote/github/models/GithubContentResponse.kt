package com.mynimef.gitstore.data.remote.github.models

import com.google.gson.annotations.SerializedName

/**
 * Represents a response from the GitHub API for repository content.
 *
 * This data class models the structure of the content tree returned by
 * GitHub's REST API, such as the `/repos/{owner}/{repo}/contents/{path}` endpoint.
 */
internal data class GithubContentResponse(

    /** The type of the content (e.g., "file", "dir"). */
    @SerializedName("type")
    val type: String,

    /** The size of the content in bytes. */
    @SerializedName("size")
    val size: Int,

    /** The name of the file or directory. */
    @SerializedName("name")
    val name: String,

    /** The full path of the file or directory in the repository. */
    @SerializedName("path")
    val path: String,

    /** The SHA hash of the content. */
    @SerializedName("sha")
    val sha: String,

    /** The Base64-encoded content, present only for files. */
    @SerializedName("content")
    val content: String? = null,

    /** API URL for the content. */
    @SerializedName("url")
    val url: String,

    /** Git URL to access the content blob or tree. */
    @SerializedName("git_url")
    val gitUrl: String? = null,

    /** HTML URL to view the content in a browser. */
    @SerializedName("html_url")
    val htmlUrl: String? = null,

    /** Direct download URL for the content. */
    @SerializedName("download_url")
    val downloadUrl: String? = null,

    /** List of nested entries, if this is a directory. */
    @SerializedName("entries")
    val entries: List<Entry>? = null,

    /** The encoding format of the content (e.g., "base64"). */
    @SerializedName("encoding")
    val encoding: String? = null,

    /** Hypermedia links for the content resource. */
    @SerializedName("_links")
    val links: Links

) {
    /**
     * Represents a single entry (file or directory) within a content tree from GitHub.
     */
    data class Entry(

        @SerializedName("type")
        val type: String,

        @SerializedName("size")
        val size: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("path")
        val path: String,

        @SerializedName("sha")
        val sha: String,

        @SerializedName("url")
        val url: String,

        @SerializedName("git_url")
        val gitUrl: String? = null,

        @SerializedName("html_url")
        val htmlUrl: String? = null,

        @SerializedName("download_url")
        val downloadUrl: String? = null,

        @SerializedName("_links")
        val links: Links

    )

    /**
     * Represents the hypermedia links associated with a GitHub content resource.
     */
    data class Links(

        @SerializedName("git")
        val git: String? = null,

        @SerializedName("html")
        val html: String? = null,

        @SerializedName("self")
        val self: String

    )

}