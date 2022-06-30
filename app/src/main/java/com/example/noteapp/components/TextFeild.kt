package com.example.noteapp.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction


@ExperimentalComposeUiApi
@Composable
fun TextFields(
    text:String,
    label:String,
    maxLine:Int=1,
    modifier: Modifier=Modifier,
    onTextChange:(String)-> Unit,
    onImeAction: ()-> Unit={},
    isError:Boolean=false
){

    val keyBoardController=LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        modifier = modifier,
        label={
            Text(text = label)
        },
        keyboardActions= KeyboardActions(onDone = {
            onImeAction()
            keyBoardController?.hide()

        }),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        maxLines = maxLine,
        isError = isError,
        trailingIcon = {
            if (isError)
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription ="Error Icon",
                    tint = MaterialTheme.colors.error)

        }
    )

}