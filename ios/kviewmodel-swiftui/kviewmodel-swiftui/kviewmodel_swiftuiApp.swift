//
//  kviewmodel_swiftuiApp.swift
//  kviewmodel-swiftui
//
//  Created by Алексей Гладков on 07.06.2022.
//

import SwiftUI

@main
struct kviewmodel_swiftuiApp: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
