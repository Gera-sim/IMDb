package com.example.imdbwitharch.ui.models

interface ToastState {
    object None: ToastState
    data class Show(val additionalMessage: String): ToastState
}