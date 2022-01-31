//
//  AdvertisementComponentSkeletonView.swift
//  iosApp
//
//  Created by Yaroslav Zotov on 31.01.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct AdvertisementComponentSkeletonView: View {
    var body: some View {
        HStack {
            AdCircle()
            Text("Some very important advertisement")
                .font(.custom("AvenirNext-Regular", size: 14))
                .foregroundColor(.secondary)
        }
        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
        .redacted(reason: .placeholder)
    }
}

private struct AdCircle: View {
    var body: some View {
        Circle()
            .fill(Color.lightPrimary)
            .frame(width: 5, height: 5)
    }
}

struct AdvertisementComponentSkeletonView_Previews: PreviewProvider {
    static var previews: some View {
        AdvertisementComponentSkeletonView()
    }
}
