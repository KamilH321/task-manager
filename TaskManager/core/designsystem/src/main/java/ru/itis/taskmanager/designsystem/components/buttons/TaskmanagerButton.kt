package ru.itis.taskmanager.designsystem.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp

@Composable
fun TaskManagerButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 6.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun TaskManagerOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled && !isLoading,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                strokeWidth = 2.dp,
                modifier = Modifier.size(20.dp),
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun TaskManagerDropdownButton(
    text: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        OutlinedButton(
            onClick = { expanded = true },
            enabled = enabled,
            modifier = Modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = if (enabled)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded)
                        Icons.Default.ArrowDropUp
                    else
                        Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun TaskManagerFilledDropdownButton(
    text: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Button(
            onClick = { expanded = true },
            enabled = enabled && !isLoading,
            modifier = Modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = if (expanded)
                            Icons.Default.ArrowDropUp
                        else
                            Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}