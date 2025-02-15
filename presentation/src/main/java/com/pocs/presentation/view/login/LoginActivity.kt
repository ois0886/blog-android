package com.pocs.presentation.view.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.composethemeadapter3.Mdc3Theme
import com.pocs.presentation.extension.RefreshStateContract
import com.pocs.presentation.model.auth.LoginUiState
import com.pocs.presentation.view.home.HomeActivity
import com.pocs.presentation.view.user.anonymous.AnonymousCreateActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    private var launcher: ActivityResultLauncher<Intent>? = null

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, true)

        showSplashUntilAuthIsReady()

        setContent {
            Mdc3Theme(this) {
                LoginScreen(
                    viewModel = viewModel,
                    onClickCreateAnonymous = ::navigateToAnonymousCreateActivity
                )
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect(::updateUi)
            }
        }

        launcher = registerForActivityResult(RefreshStateContract()) {
            if (it != null) {
                it.message?.let { message ->
                    viewModel.showUserMessage(message)
                }
            }
        }
    }

    private fun showSplashUntilAuthIsReady() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    val hideSplashScreen = viewModel.uiState.value.hideSplashScreen

                    return if (hideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun updateUi(uiState: LoginUiState) {
        if (uiState.isLoggedIn) {
            navigateToHomeActivity()
        }
    }

    private fun navigateToHomeActivity() {
        val intent = HomeActivity.getIntent(this)
        startActivity(intent)
        finish()
    }

    private fun navigateToAnonymousCreateActivity() {
        val intent = AnonymousCreateActivity.getIntent(this)
        launcher?.launch(intent)
    }
}
