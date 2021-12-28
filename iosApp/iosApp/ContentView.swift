import SwiftUI
import shared


struct ContentView: View {
    @ObservedObject var authStatus: AuthStatusObservable
    
    init() {
        authStatus = AuthStatusObservable()
    }

    
    var body: some View {
        HStack {
            if authStatus.state is AuthStatusState.Loading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle())
                    .scaleEffect(x: 1.5, y: 1.5, anchor: .center)
            }
            
            else if authStatus.state is AuthStatusState.NotAuthorized {
                LoginScreenView()
            }
            
            else {
                MainView()
            }
        }
        .environmentObject(authStatus)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
