# GitStore

GitStore is an Android application designed to serve as an app store for discovering and fetching apps from public GitHub repositories. It allows users to search for apps described in a special metadata file (e.g., `gitstore.json`) and view their details.

## Features

- **GitHub Integration**: Connect to GitHub using a personal access token to search for and fetch app information.
- **App Discovery**: Search for apps stored in public GitHub repositories using a custom metadata file format.
- **App Details**: View detailed information about each app, including its name and description.
- **User Authentication**: Manage GitHub authentication to access private repositories or perform authenticated searches.

## Future Plans

- **GitLab Integration**: Planned integration with GitLab to fetch apps from GitLab repositories.
- **Bitbucket Integration**: Planned integration with Bitbucket to fetch apps from Bitbucket repositories.
- **Other Platforms**: Additional integrations with other platforms are also planned for future releases.

## Getting Started

### Prerequisites

- Android Studio
- A GitHub personal access token (for authenticated API access)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/GitStore.git
   ```
2. Open the project in Android Studio.
3. Build and run the application on your device or emulator.

### Configuration

- Ensure you have a valid GitHub personal access token. You can generate one in your GitHub account settings.
- Configure the token in the app settings or as required by the application.

## Usage

- Launch the app and navigate to the search screen.
- Enter a search query to find apps stored in GitHub repositories.
- View the list of results and tap on an app to see its details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Thanks to all contributors who have helped in the development of GitStore.
- Special thanks to the GitHub API for providing the necessary endpoints for app discovery. 