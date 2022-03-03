package xyz.teamgravity.responsivescreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.teamgravity.responsivescreen.ui.theme.ResponsiveScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResponsiveScreenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val windowInfo = rememberWindowInfo()

                    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                        ItemList(
                            item1 = {
                                ItemCard(
                                    order = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Cyan)
                                        .padding(16.dp)
                                )
                            },
                            item2 = {
                                ItemCard(
                                    order = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Green)
                                        .padding(16.dp)
                                )
                            }
                        )
                    } else {
                        ItemListParallel(
                            item1 = {
                                ItemCard(
                                    order = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Cyan)
                                        .padding(16.dp)
                                )
                            },
                            item2 = {
                                ItemCard(
                                    order = it,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Green)
                                        .padding(16.dp)
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ItemList(
    item1: @Composable (order: Int) -> Unit,
    item2: @Composable (order: Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(10) {
            item1(it)
        }
        items(10) {
            item2(it)
        }
    }
}

@Composable
fun ItemListParallel(
    item1: @Composable (order: Int) -> Unit,
    item2: @Composable (order: Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(10) {
                item1(it)
            }
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(10) {
                item2(it)
            }
        }
    }
}

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    order: Int
) {
    Text(
        text = "Item $order",
        modifier = modifier,
        fontSize = 25.sp
    )
}