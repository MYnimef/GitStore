package com.mynimef.gitstore.data.remote.github.models

import com.google.gson.annotations.SerializedName

/**
 * Represents an authenticated GitHub user returned from the `GET /user` endpoint.
 *
 * @property login The username of the user.
 * @property id The unique numeric ID of the user.
 * @property nodeId The node ID used internally by GitHub.
 * @property avatarUrl URL of the user's avatar.
 * @property gravatarId Gravatar ID, if any (usually empty).
 * @property url API URL to fetch this user.
 * @property htmlUrl Public URL of the user's GitHub profile.
 * @property followersUrl API URL to fetch the user's followers.
 * @property followingUrl API URL to fetch who the user is following.
 * @property gistsUrl API URL for the user's gists.
 * @property starredUrl API URL for the user's starred repos.
 * @property subscriptionsUrl API URL for the user's subscriptions.
 * @property organizationsUrl API URL for the user's orgs.
 * @property reposUrl API URL for the user's repos.
 * @property eventsUrl API URL for the user's events.
 * @property receivedEventsUrl API URL for the user's received events.
 * @property type Type of user, typically "User".
 * @property siteAdmin Whether the user is a GitHub site admin.
 * @property name The user's display name.
 * @property company The user's company.
 * @property blog Blog or website URL.
 * @property location Location as set in the user's profile.
 * @property email Public email (may be null).
 * @property hireable Whether the user is hireable (may be null).
 * @property bio User's bio text.
 * @property twitterUsername Twitter handle (may be null).
 * @property publicRepos Count of public repositories.
 * @property publicGists Count of public gists.
 * @property followers Number of followers.
 * @property following Number of users the user is following.
 * @property createdAt Account creation date (ISO 8601).
 * @property updatedAt Last update date of the profile (ISO 8601).
 */
data class GitHubUser(

    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("node_id")
    val nodeId: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("gravatar_id")
    val gravatarId: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("html_url")
    val htmlUrl: String,

    @SerializedName("followers_url")
    val followersUrl: String,

    @SerializedName("following_url")
    val followingUrl: String,

    @SerializedName("gists_url")
    val gistsUrl: String,

    @SerializedName("starred_url")
    val starredUrl: String,

    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,

    @SerializedName("organizations_url")
    val organizationsUrl: String,

    @SerializedName("repos_url")
    val reposUrl: String,

    @SerializedName("events_url")
    val eventsUrl: String,

    @SerializedName("received_events_url")
    val receivedEventsUrl: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("site_admin")
    val siteAdmin: Boolean,

    @SerializedName("name")
    val name: String?,

    @SerializedName("company")
    val company: String?,

    @SerializedName("blog")
    val blog: String?,

    @SerializedName("location")
    val location: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("hireable")
    val hireable: Boolean?,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("twitter_username")
    val twitterUsername: String?,

    @SerializedName("public_repos")
    val publicRepos: Int,

    @SerializedName("public_gists")
    val publicGists: Int,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String

)