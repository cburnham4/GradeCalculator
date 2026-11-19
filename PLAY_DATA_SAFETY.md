# Google Play Data Safety Responses

**App:** Grade Calculator (`letshangllc.gradecalculator`)  
**Version:** 3.0.0 (versionCode 100)  
**Last Updated:** November 2026  
**Target SDK:** 36

This document provides responses for the Google Play Console Data Safety section.

---

## Overview

Grade Calculator is a local grade / GPA calculator. It does **not** create accounts or sync user grades to a backend. It displays ads via Google AdMob and gathers advertising consent via Google’s User Messaging Platform (UMP).

Amazon Mobile Ads was removed; all ad traffic uses the Google Mobile Ads SDK over HTTPS.

---

## Data Collection Summary

| Data Type | Collected | Shared | Purpose |
|-----------|-----------|--------|---------|
| App interactions | Yes (AdMob) | Yes (AdMob) | Advertising |
| Advertising ID | Yes (AdMob) | Yes (AdMob) | Advertising |
| Device or other IDs | Yes (AdMob) | Yes (AdMob) | Advertising |
| Grades / GPA inputs | No (stay on device) | No | App functionality only |

The calculator fields (grades, weights, letter grades, credit hours) are entered by the user and are not uploaded by this app.

---

## Detailed Data Types

### 1. App Activity

#### App Interactions
- **Collected:** Yes (via Google AdMob / ads measurement)
- **Shared:** Yes (with Google AdMob for ad serving / personalization where allowed)
- **Purpose:** Advertising
- **Optional:** Subject to GDPR/consent (UMP)
- **User Control:** UMP consent dialog allows users in applicable regions to opt out of personalized ads
- **Encrypted in transit:** Yes (HTTPS)

### 2. Device or Other IDs

#### Advertising ID
- **Collected:** Yes (via Google AdMob)
- **Shared:** Yes (with Google for advertising)
- **Purpose:** Advertising
- **Optional:** Users can reset or opt out via device settings; UMP consent in EU/EEA
- **Encrypted in transit:** Yes (HTTPS)

---

## Third-Party SDKs

### Google AdMob (play-services-ads)
- **Data collected:** Advertising ID, app interactions, device / coarse location signals as described by Google
- **Purpose:** Display advertisements (adaptive banners)
- **Shared:** Yes, with Google advertising partners per Google’s policies
- **User Control:** UMP consent flow for GDPR compliance
- **Privacy Policy:** https://policies.google.com/privacy

### Google User Messaging Platform (UMP)
- **Purpose:** GDPR / privacy messaging and consent management before requesting ads
- **Data collected:** Consent preferences
- **Privacy Policy:** https://policies.google.com/privacy

---

## Permissions (Play declarations)

Declared in the app manifest:

| Permission | Why |
|------------|-----|
| `INTERNET` | Load ads and consent forms |
| `ACCESS_NETWORK_STATE` | Ads SDK network checks |

Not declared by this app (confirm after merging dependency manifests):

- `POST_NOTIFICATIONS` — not used by Grade Calculator UI
- `ACCESS_WIFI_STATE` — removed (was used for Amazon Ads)
- `AD_ID` — may be merged from play-services-ads; declare Advertising ID usage in Play Console if present

If Play Console flags additional permissions from the Mobile Ads SDK (e.g. `com.google.android.gms.permission.AD_ID`), declare them accurately under Data safety / Advertising ID.

---

## Data Security

### Encryption
- **In transit:** Yes — `usesCleartextTraffic="false"`; Mobile Ads / UMP use HTTPS
- **At rest:** Calculator inputs remain in on-device UI state only (not persisted by this app to a cloud service)

### Data Deletion
There is no cloud account. Users can clear app data / uninstall. Ad-related identifiers are controlled via device ad settings and the UMP flow where applicable.

---

## Children's Privacy / Families

This app is **not** directed at children under 13 (COPPA) or under 16 (GDPR) as a child-directed experience.

- `setTagForUnderAgeOfConsent(false)` is configured in UMP
- No child-directed ad serving is enabled in code
- If listing under Families / Designed for Families, review AdMob Families policies and use appropriate ad settings before publishing

---

## Regional Compliance

### GDPR (EU/EEA)
- UMP consent dialog is shown when required before personalized ads
- Ads are only requested when `consentInformation.canRequestAds()` is true

### CCPA (California)
- Users can opt out of personalized ads via device-level advertising settings and applicable consent UX

---

## Play Console Form Responses

### Does your app collect or share any of the required user data types?
**Yes** (via AdMob / advertising)

### Is all of the user data collected by your app encrypted in transit?
**Yes**

### Do you provide a way for users to request that their data is deleted?
**Yes** — uninstall / clear app data; no developer-hosted user account. Contact the Play Store listing email for ad-related questions.

### Data Types to Declare

1. **App interactions**
   - Collected: Yes
   - Shared: Yes (Google/AdMob)
   - Purpose: Advertising

2. **Device or other IDs** (Advertising ID)
   - Collected: Yes
   - Shared: Yes (Google/AdMob)
   - Purpose: Advertising
   - Optional / user can opt out: Yes (device settings + UMP where required)

---

## Contact

For privacy inquiries, contact the developer via the email address listed on the Google Play Store listing.
