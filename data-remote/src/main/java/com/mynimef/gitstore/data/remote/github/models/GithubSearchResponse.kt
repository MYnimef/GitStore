package com.mynimef.gitstore.data.remote.github.models

/**
 * Represents the response from GitHub's code search API.
 *
 * @property totalCount The total number of matching search results.
 * @property incompleteResults Whether the results are incomplete due to search limitations.
 * @property items A list of code file results that matched the search query.
 */
internal data class GithubSearchResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
) {

    /**
     * Represents a single code search result item.
     *
     * @property name The file name (e.g., gitstore.json).
     * @property path The file path within the repository.
     * @property html_url The URL to view the file on GitHub.
     * @property repository The repository containing the file.
     */
    data class Item(
        val name: String,
        val path: String,
        val html_url: String,
        val repository: Repository
    )

    /**
     * Represents a GitHub repository that contains the matched file.
     *
     * @property id The unique ID of the repository.
     * @property full_name The full name of the repository in "owner/repo" format.
     * @property html_url The URL to view the repository on GitHub.
     */
    data class Repository(
        val id: Long,
        val full_name: String,
        val html_url: String
    )

}
