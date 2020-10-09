[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=plastic)](https://android-arsenal.com/api?level=26)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

### Requirements
- An active [Sendbird](https://dashboard.sendbird.com/auth/signup) account
- An Android development IDE like [Android Studio](https://developer.android.com/studio/)

### Getting Started
1. Create or select an application from your Sendbird Dashboard
2. Add test users to the selected application*

** If you have a .csv of users, they can be uploaded using a python script from this [repo](https://github.com/jalakoo/sendbird_bulk_user_uploader). Alternatively, you can add any number of random users with the python script[here](https://github.com/jalakoo/sendbird_random_users) 

### Structure
This repo is broken up into 3 folders:
**1_start**/ contains a slightly modified `Basic Activity` starter app
**2_chat**/ contains the above app with Sendbird's UIKit + default chat feature added
**3_chat+translation**/ contains the final sample with translation services from Sendbird's Chat SDK integrated with Sendbird's UIKit

## License
Apache 2.0 License.
