package com.example.shoppinglist.ui.ui_elements

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.shoppinglist.ui.viewmodel.ItemViewModel
import com.example.shoppinglist.ui.theme.BackgroundColor
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: ItemViewModel
) {
    val items = viewModel.items.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Shopping List",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = BackgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Vérifiez si la liste est vide
            if (items.value.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f) // Prend tout l'espace disponible au-dessus du bouton
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Votre liste est vide.",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            } else {
                // Afficher la liste
                ItemList(
                    items = items.value,
                    onDeleteItem = { viewModel.deleteItem(it) },
                    onItemChecked = { item, checked ->
                        viewModel.updateItem(item.copy(isChecked = checked))
                    },
                    onQuantityChange = { item, quantity ->
                        viewModel.updateItem(item.copy(quantity = quantity))
                    }
                )
            }

            // Le formulaire d'ajout reste toujours visible
            AddItemForm(
                onAddItem = { name, quantity ->
                    viewModel.addItem(name, quantity)
                },

                )
        }
    }
}
