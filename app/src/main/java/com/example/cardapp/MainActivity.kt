package com.example.cardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardapp.ui.theme.CardAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeMaker()
                }
            }
        }
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)

    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 30.sp,
            lineHeight = 116.sp,
        )
        Text(
            text = from,
            fontSize = 20.sp,
            lineHeight = 20.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End)
        )
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.bg_compose_background)

    Box(modifier = modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 1F
        )
    }
}

@Composable
fun Article() {
    Column() {
        Banner(modifier = Modifier.height(height = 48.dp))
        Text(
            text = stringResource(R.string.title),
            modifier = Modifier.padding(
                paddingValues = PaddingValues(all = 6.dp)
            )
        )
        Text(
            text = stringResource(R.string.introduction), modifier = Modifier.padding(
                paddingValues = PaddingValues(all = 6.dp)
            )
        )
        Text(
            text = stringResource(R.string.body), modifier = Modifier.padding(
                paddingValues = PaddingValues(all = 6.dp)
            )
        )
    }
}

@Composable
fun TaskCompletionConfirmation(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.ic_task_completed)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = image,
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.task_completion_header),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(
                paddingValues = PaddingValues(top = 24.dp, bottom = 8.dp)
            )
        )
        Text(text = stringResource(R.string.task_completion_subheading), fontSize = 16.sp)
    }
}

@Composable
fun Quadrant(title: String, content: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(paddingValues = PaddingValues(all = 16.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
        Text(text = content, textAlign = TextAlign.Justify)
    }
}

@Composable
fun QuadrantGrid(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.weight(1f)) {
            Quadrant(
                title = stringResource(R.string.text_title),
                content = stringResource(R.string.text_content),
                modifier = Modifier
                    .background(color = Color(0xFFEADDFF))
                    .weight(1f)
            )
            Quadrant(
                title = stringResource(R.string.image_title),
                content = stringResource(R.string.image_content),
                modifier = Modifier
                    .background(color = Color(0xFFD0BCFF))
                    .weight(1f)
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            Quadrant(
                title = stringResource(R.string.row_title),
                content = stringResource(R.string.row_content),
                modifier = Modifier
                    .background(color = Color(0xFFB69DF8))
                    .weight(1f)
            )
            Quadrant(
                title = stringResource(R.string.column_title),
                content = stringResource(R.string.column_content),
                modifier = Modifier
                    .background(color = Color(0xFFF6EDFF))
                    .weight(1f)
            )
        }
    }
}

@Composable
fun DiceRoller(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }

    val imageResource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    val image = painterResource(imageResource)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = result.toString(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(
                text = stringResource(R.string.roll_label)
            )
        }
    }
}

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    image: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    bgColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(paddingValues = PaddingValues(all = 4.dp)),
        shape = RoundedCornerShape(size = 16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = bgColor)

    ) {
        Image(
            painter = image,
            contentDescription = contentDescription,
        )
    }
}

@Composable
fun LemonadeMaker(modifier: Modifier = Modifier) {
    var stage by remember { mutableStateOf(1) }

    val imageResource = when (stage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val contentDescription = when (stage) {
        1 -> stringResource(R.string.lemon_tree_description)
        2 -> stringResource(R.string.lemon_description)
        3 -> stringResource(R.string.lemon_glass_description)
        else -> stringResource(R.string.lemon_empty_description)
    }

    val instruction = when (stage) {
        1 -> stringResource(R.string.lemon_tree_instruction)
        2 -> stringResource(R.string.lemon_instruction)
        3 -> stringResource(R.string.lemon_glass_instruction)
        else -> stringResource(R.string.lemon_restart_instruction)
    }

    var squeezeCount = 0;
    var targetSqueezeCount = (2..4).random()

    val handleClick: () -> Unit = {
        if (stage == 1 || stage == 3) {
            stage++
        } else if (stage == 2) {
            squeezeCount++
            if (squeezeCount == targetSqueezeCount) {
                stage++
            }
        } else {
            stage = 1
            squeezeCount = 0;
            targetSqueezeCount = (2..4).random()
        }

    }

    Column() {
        Text(
            text = "Lemonade",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .background(color = Color.Yellow)
                .fillMaxWidth()
                .padding(paddingValues = PaddingValues(all = 16.dp))
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageButton(
                image = painterResource(imageResource),
                contentDescription = contentDescription,
                onClick = handleClick,
                bgColor = Color.Cyan
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(instruction, fontSize = 18.sp)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CardAppTheme {
        LemonadeMaker()
    }
}