package com.bbou.material.builder

// A C C E N T

// Light tone, dark tone, palette index (1= primary, 2=secondary, 3=tertiary)
// Role definitions: Key -> (LightTone, DarkTone, Palette)
val accentRoleDefs = mapOf(
    "primary" to Triple(40, 80, 1),
    "onPrimary" to Triple(100, 20, 1),
    "primaryContainer" to Triple(90, 30, 1),
    "onPrimaryContainer" to Triple(10, 90, 1),
    "inversePrimary" to Triple(80, 40, 1),
    "primaryFixed" to Triple(90, 90, 1),
    "onPrimaryFixed" to Triple(10, 10, 1),
    "primaryFixedDim" to Triple(80, 80, 1),
    "onPrimaryFixedVariant" to Triple(30, 30, 1),

    "secondary" to Triple(40, 80, 2),
    "onSecondary" to Triple(100, 20, 2),
    "secondaryContainer" to Triple(90, 30, 2),
    "onSecondaryContainer" to Triple(10, 90, 2),
    "secondaryFixed" to Triple(90, 90, 2),
    "onSecondaryFixed" to Triple(10, 10, 2),
    "secondaryFixedDim" to Triple(80, 80, 2),
    "onSecondaryFixedVariant" to Triple(30, 30, 2),

    "tertiary" to Triple(40, 80, 3),
    "onTertiary" to Triple(100, 20, 3),
    "tertiaryContainer" to Triple(90, 30, 3),
    "onTertiaryContainer" to Triple(10, 90, 3),
    "tertiaryFixed" to Triple(90, 90, 3),
    "onTertiaryFixed" to Triple(10, 10, 3),
    "tertiaryFixedDim" to Triple(80, 80, 3),
    "onTertiaryFixedVariant" to Triple(30, 30, 3),
)

val accentRoleMediumContrastDefs = mapOf(
    "inversePrimary_mediumContrast" to Triple(80, 40, 1),
    "primary_mediumContrast" to Triple(26, 90, 1),
    "onPrimary_mediumContrast" to Triple(100, 0, 1),
    "primaryContainer_mediumContrast" to Triple(54, 54, 1),
    "onPrimaryContainer_mediumContrast" to Triple(0, 100, 1),
    "primaryFixed_mediumContrast" to Triple(74, 74, 1),
    "onPrimaryFixed_mediumContrast" to Triple(0, 0, 1),
    "primaryFixedDim_mediumContrast" to Triple(58, 58, 1),
    "onPrimaryFixedVariant_mediumContrast" to Triple(10, 10, 1),

    "secondary_mediumContrast" to Triple(26, 90, 2),
    "onSecondary_mediumContrast" to Triple(100, 0, 2),
    "secondaryContainer_mediumContrast" to Triple(54, 54, 2),
    "onSecondaryContainer_mediumContrast" to Triple(0, 100, 2),
    "secondaryFixed_mediumContrast" to Triple(74, 74, 2),
    "onSecondaryFixed_mediumContrast" to Triple(0, 0, 2),
    "secondaryFixedDim_mediumContrast" to Triple(58, 58, 2),
    "onSecondaryFixedVariant_mediumContrast" to Triple(10, 10, 2),

    "tertiary_mediumContrast" to Triple(2, 90, 3),
    "onTertiary_mediumContrast" to Triple(100, 0, 3),
    "tertiaryContainer_mediumContrast" to Triple(54, 54, 3),
    "onTertiaryContainer_mediumContrast" to Triple(0, 100, 3),
    "tertiaryFixed_mediumContrast" to Triple(74, 74, 3),
    "onTertiaryFixed_mediumContrast" to Triple(0, 0, 3),
    "tertiaryFixedDim_mediumContrast" to Triple(58, 58, 3),
    "onTertiaryFixedVariant_mediumContrast" to Triple(10, 10, 3),
)

val accentRoleHighContrastDefs = mapOf(
    "inversePrimary_highContrast" to Triple(90, 20, 1),
    "primary_highContrast" to Triple(0, 100, 1),
    "onPrimary_highContrast" to Triple(100, 0, 1),
    "primaryContainer_highContrast" to Triple(36, 74, 1),
    "onPrimaryContainer_highContrast" to Triple(100, 0, 1),
    "primaryFixed_highContrast" to Triple(54, 54, 1),
    "onPrimaryFixed_highContrast" to Triple(100, 100, 1),
    "primaryFixedDim_highContrast" to Triple(36, 36, 1),
    "onPrimaryFixedVariant_highContrast" to Triple(0, 0, 1),

    "secondary_highContrast" to Triple(0, 100, 2),
    "onSecondary_highContrast" to Triple(100, 0, 2),
    "secondaryContainer_highContrast" to Triple(36, 74, 2),
    "onSecondaryContainer_highContrast" to Triple(100, 0, 2),
    "secondaryFixed_highContrast" to Triple(54, 54, 2),
    "onSecondaryFixed_highContrast" to Triple(100, 100, 2),
    "secondaryFixedDim_highContrast" to Triple(36, 36, 2),
    "onSecondaryFixedVariant_highContrast" to Triple(0, 0, 2),

    "tertiary_highContrast" to Triple(0, 100, 3),
    "onTertiary_highContrast" to Triple(100, 0, 3),
    "tertiaryContainer_highContrast" to Triple(36, 74, 3),
    "onTertiaryContainer_highContrast" to Triple(100, 0, 3),
    "tertiaryFixed_highContrast" to Triple(54, 54, 3),
    "onTertiaryFixed_highContrast" to Triple(100, 100, 3),
    "tertiaryFixedDim_highContrast" to Triple(36, 36, 3),
    "onTertiaryFixedVariant_highContrast" to Triple(0, 0, 3),
)

val accentRoles = accentRoleDefs.keys.toList()

val accentRolesMediumContrast = accentRoleMediumContrastDefs.keys.toList()

val accentRolesHighContrast = accentRoleHighContrastDefs.keys.toList()

val primaryAccentRoles = listOf(
    "primary",
    "onPrimary",
    "primaryContainer",
    "onPrimaryContainer",
)

val secondaryAccentRoles = listOf(
    "secondary",
    "onSecondary",
    "secondaryContainer",
    "onSecondaryContainer",
)

val tertiaryAccentRoles = listOf(
    "tertiary",
    "onTertiary",
    "tertiaryContainer",
    "onTertiaryContainer",
)

var accentRolesMin = primaryAccentRoles + secondaryAccentRoles + tertiaryAccentRoles

// S U R F A C E

// Handled by SchemeContent
val surfaceRoles = listOf(
    "surface",
    "onSurface",
    "onSurface",
    "surfaceVariant",
    "onSurfaceVariant",
    "inverseSurface",
    "inverseOnSurface",
    "surfaceDim",
    "surfaceBright",
    "surfaceContainerLowest",
    "surfaceContainerLow",
    "surfaceContainer",
    "surfaceContainerHigh",
    "surfaceContainerHighest",

    "background",
    "onBackground",

    "outline",
    "outlineVariant",

    "scrim",

    "error",
    "onError",
    "errorContainer",
    "onErrorContainer",
)

val surfaceRolesMin = listOf("surface", "onSurface", "surfaceContainer", "background", "outline")

val roles = surfaceRoles + accentRoles

val rolesMin = surfaceRolesMin + primaryAccentRoles

const val namePrefix = "md_theme_"
