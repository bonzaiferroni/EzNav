package com.bollwerks.eznav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

data class DrawerConfig(
    val mainAppIcon: (@Composable () -> Painter)? = null,
    val drawerItems: List<DrawerItemConfig> = emptyList(),
)

data class DrawerItemConfig(
    val drawerOption: EzRoute,
    val title: String,
    val icon: ImageVector,
)

@Composable
fun EzDrawer(
    initialRoute: EzRoute,
    drawerConfig: DrawerConfig,
    navHostConfig: NavHostConfig,
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                drawerState = drawerState,
                menuItems = drawerConfig.drawerItems,
                initialRoute = initialRoute,
                config = drawerConfig,
            ) { onUserPickedOption ->
                navController.navigate(onUserPickedOption.name)
            }
        }
    ) {
        EzNavHost(
            navController = navController,
            navHostConfig = navHostConfig,
            drawerState = drawerState,
        )
    }
}

@Composable
fun DrawerContent(
    drawerState: DrawerState,
    menuItems: List<DrawerItemConfig>,
    initialRoute: EzRoute,
    config: DrawerConfig,
    onClick: (EzRoute) -> Unit
) {
    var currentPick by remember { mutableStateOf(initialRoute) }
    val coroutineScope = rememberCoroutineScope()
    val mainAppIcon = config.mainAppIcon

    ModalDrawerSheet {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (mainAppIcon != null) {
                    Image(
                        painter = mainAppIcon(),
                        contentDescription = "Main app icon",
                        modifier = Modifier.size(150.dp)
                    )
                }
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // generates on demand the required composables
                    items(menuItems) { item ->
                        // custom UI representation of the button
                        DrawerItem(item = item) { navOption ->

                            // if it is the same - ignore the click
                            if (currentPick == navOption) {
                                return@DrawerItem
                            }

                            currentPick = navOption

                            // close the drawer after clicking the option
                            coroutineScope.launch {
                                drawerState.close()
                            }

                            // navigate to the required screen
                            onClick(navOption)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerItem(item: DrawerItemConfig, onClick: (EzRoute) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.width(150.dp),
        onClick = { onClick(item.drawerOption) },
        shape = RoundedCornerShape(50),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
    }
}