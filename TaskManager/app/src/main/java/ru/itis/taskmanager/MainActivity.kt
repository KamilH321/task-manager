package ru.itis.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.itis.taskmanager.designsystem.theme.TaskManagerTheme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ru.itis.taskmanager.designsystem.components.buttons.*
import ru.itis.taskmanager.designsystem.components.cards.TaskManagerCard
import ru.itis.taskmanager.designsystem.components.cards.TaskManagerStyledCard
import ru.itis.taskmanager.designsystem.components.inputs.TaskManagerTextField
import ru.itis.taskmanager.designsystem.components.lists.TaskManagerListItem
import ru.itis.taskmanager.designsystem.theme.TaskManagerSpacing


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UIKitShowcase()
        }
    }
}

@Composable
fun UIKitShowcase() {
    TaskManagerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(TaskManagerSpacing.md),
                verticalArrangement = Arrangement.spacedBy(TaskManagerSpacing.md)
            ) {
                // Кнопки
                item {
                    Text(
                        text = "Buttons",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    TaskManagerButton(
                        text = "Primary Button",
                        onClick = {}
                    )
                }

                item {
                    TaskManagerButton(
                        text = "Disabled Button",
                        onClick = {},
                        enabled = false
                    )
                }

                item {
                    TaskManagerButton(
                        text = "Loading Button",
                        onClick = {},
                        isLoading = true
                    )
                }

                item {
                    TaskManagerOutlinedButton(
                        text = "Outlined Button",
                        onClick = {}
                    )
                }

                item {
                    TaskManagerOutlinedButton(
                        text = "Disabled Outlined",
                        onClick = {},
                        enabled = false
                    )
                }

                item {
                    TaskManagerOutlinedButton(
                        text = "Loading Outlined",
                        onClick = {},
                        isLoading = true
                    )
                }

                // Dropdown Buttons
                item {
                    Text(
                        text = "Dropdown Buttons",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = TaskManagerSpacing.md, bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    var selectedOption by remember { mutableStateOf("Выберите статус") }
                    val options = listOf("К выполнению", "В процессе", "На проверке", "Завершено")

                    TaskManagerDropdownButton(
                        text = selectedOption,
                        options = options,
                        onOptionSelected = { selectedOption = it }
                    )
                }

                item {
                    var selectedPriority by remember { mutableStateOf("Выберите приоритет") }
                    val priorities = listOf("Низкий", "Средний", "Высокий", "Критический")

                    TaskManagerFilledDropdownButton(
                        text = selectedPriority,
                        options = priorities,
                        onOptionSelected = { selectedPriority = it }
                    )
                }

                item {
                    var selectedCategory by remember { mutableStateOf("Категория") }
                    val categories = listOf("Работа", "Личное", "Обучение", "Здоровье")

                    TaskManagerDropdownButton(
                        text = selectedCategory,
                        options = categories,
                        onOptionSelected = { selectedCategory = it },
                        enabled = false
                    )
                }


                // Карточки
                item {
                    Text(
                        text = "Cards",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = TaskManagerSpacing.md, bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    TaskManagerCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Card Content",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(TaskManagerSpacing.md)
                        )
                    }
                }

                // Стилизованная карточка
                item {
                    Text(
                        text = "Styled Card",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = TaskManagerSpacing.md, bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    TaskManagerStyledCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(TaskManagerSpacing.md),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Стилизованная карточка",
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(TaskManagerSpacing.sm))
                            Text(
                                text = "Скругленные углы 20dp\nЦвет фона: SurfaceVariant\nОбводка: OnSurfaceVariant",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Поля ввода
                item {
                    Text(
                        text = "Text Fields",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = TaskManagerSpacing.md, bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    var text by remember { mutableStateOf("") }
                    TaskManagerTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = "Standard Field"
                    )
                }

                item {
                    var errorText by remember { mutableStateOf("") }
                    TaskManagerTextField(
                        value = errorText,
                        onValueChange = { errorText = it },
                        label = "Field with Error",
                        isError = errorText.isBlank(),
                        errorMessage = if (errorText.isBlank()) "This field cannot be empty" else null
                    )
                }

                // Элементы списка
                item {
                    Text(
                        text = "List Items",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = TaskManagerSpacing.md, bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        TaskManagerListItem(
                            title = "Task Title",
                            subtitle = "Task description or additional info"
                        )
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        TaskManagerListItem(
                            title = "Task Without Subtitle"
                        )
                    }
                }

                // Typography демонстрация
                item {
                    Text(
                        text = "Typography",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = TaskManagerSpacing.md, bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(TaskManagerSpacing.sm)
                    ) {
                        Text("Display Large", style = MaterialTheme.typography.displayLarge)
                        Text("Display Medium", style = MaterialTheme.typography.displayMedium)
                        Text("Display Small", style = MaterialTheme.typography.displaySmall)
                        Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
                        Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
                        Text("Headline Small", style = MaterialTheme.typography.headlineSmall)
                        Text("Body Large", style = MaterialTheme.typography.bodyLarge)
                        Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
                        Text("Body Small", style = MaterialTheme.typography.bodySmall)
                        Text("Label Large", style = MaterialTheme.typography.labelLarge)
                        Text("Label Medium", style = MaterialTheme.typography.labelMedium)
                    }
                }

                // Цветовая палитра
                item {
                    Text(
                        text = "Color Palette",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(top = TaskManagerSpacing.md, bottom = TaskManagerSpacing.sm)
                    )
                }

                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(TaskManagerSpacing.xs)
                    ) {
                        ColorRow("Primary", MaterialTheme.colorScheme.primary)
                        ColorRow("On Primary", MaterialTheme.colorScheme.onPrimary)
                        ColorRow("Secondary", MaterialTheme.colorScheme.secondary)
                        ColorRow("Tertiary", MaterialTheme.colorScheme.tertiary)
                        ColorRow("Error", MaterialTheme.colorScheme.error)
                        ColorRow("Background", MaterialTheme.colorScheme.background)
                        ColorRow("Surface", MaterialTheme.colorScheme.surface)
                        ColorRow("Surface Variant", MaterialTheme.colorScheme.surfaceVariant)
                    }
                }
            }
        }
    }
}

@Composable
private fun ColorRow(name: String, color: androidx.compose.ui.graphics.Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(TaskManagerSpacing.sm)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color)
        )
        Text(
            text = "$name: ${color.value.toLong().toString(16).takeLast(6)}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}