import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init() {
        startKoin()
        NapierKt.debugBuild()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
