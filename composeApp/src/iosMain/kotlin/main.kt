import androidx.compose.ui.window.ComposeUIViewController
import com.ttllab.notionapitest.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
