/*
 * Copyright 2017 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.realm.objectserver;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;

import io.realm.Realm;
import io.realm.SyncManager;

class BaseIntegrationTest {
    protected Context context;
    // We cannot use test looper's temp directory since deletion of the temp folder will cause sync client crash in
    // the next test. (make_dir failed.)
    private static File rootFolder;

    BaseIntegrationTest() {
        context = InstrumentationRegistry.getContext();

    }

    @BeforeClass
    public static void setUp () throws Exception {
        SyncManager.Debug.skipOnlineChecking = true;
        Realm.init(InstrumentationRegistry.getContext());
        rootFolder = File.createTempFile("SyncIntegrationTests", "");
        //noinspection ResultOfMethodCallIgnored
        rootFolder.delete();
        //noinspection ResultOfMethodCallIgnored
        rootFolder.mkdir();
    }

    @AfterClass
    public static void tearDown () throws Exception {
    }

    static File getRoot() {
        return rootFolder;
    }
}