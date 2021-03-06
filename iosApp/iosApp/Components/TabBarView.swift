import SwiftUI

struct TabBarView: View {
    @Binding var selectedTab: String
    
    @Environment(\.colorScheme) var colorScheme
    
    private var bg: Color {
        get {
            colorScheme == .light ? Color.white.opacity(0.01) : Color(hex: 0xFF211E2B)
        }
    }
    
    var body: some View {
        HStack (spacing: 0) {
            TabIcon(selectedTab: $selectedTab, icon: selectedTab == "home" ? "home_active_icon" : "home_disabled_icon", tab: "home")
            TabIcon(selectedTab: $selectedTab, icon: selectedTab == "marks" ? "marks_active_icon" : "marks_disabled_icon", tab: "marks")
            TabIcon(selectedTab: $selectedTab, icon: selectedTab == "profile" ? "user_active_icon" : "user_disabled_icon", tab: "profile")
        }
        .padding(.top, 15)
        .padding(.bottom, getSafeArea().bottom == 0 ? 15 : getSafeArea().bottom)
    }
}

extension View {
    func getSafeArea()-> UIEdgeInsets {
        return UIApplication.shared.windows.first?.safeAreaInsets ?? UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
    }
}

struct TabIcon: View {
    @Binding var selectedTab: String
    var icon: String
    var tab: String
    
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        Button(action: { selectedTab = tab }, label: {
            VStack(spacing: 8){
                Image(icon)
                    .resizable()
                    .foregroundColor(colorScheme == .light ? Color(hex: 0xFFCBCBCB) : Color(hex: 0xFF90919A))
                    .aspectRatio(contentMode: .fit)
                    .frame(width:36, height: 36)
                    
            }
            .frame(maxWidth: .infinity)
        })
    }
}

struct TabBarView_Previews: PreviewProvider {
    static var previews: some View {
        TabBarView(selectedTab: .constant("home"))
    }
}
