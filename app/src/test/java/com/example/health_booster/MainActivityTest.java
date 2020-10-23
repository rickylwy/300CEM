package com.example.health_booster;

import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzw;
import com.google.android.gms.internal.maps.zzz;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.internal.zzab;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.internal.zzaf;
import com.google.android.gms.maps.internal.zzaj;
import com.google.android.gms.maps.internal.zzal;
import com.google.android.gms.maps.internal.zzan;
import com.google.android.gms.maps.internal.zzap;
import com.google.android.gms.maps.internal.zzar;
import com.google.android.gms.maps.internal.zzat;
import com.google.android.gms.maps.internal.zzav;
import com.google.android.gms.maps.internal.zzax;
import com.google.android.gms.maps.internal.zzaz;
import com.google.android.gms.maps.internal.zzbb;
import com.google.android.gms.maps.internal.zzbd;
import com.google.android.gms.maps.internal.zzbf;
import com.google.android.gms.maps.internal.zzbs;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzh;
import com.google.android.gms.maps.internal.zzl;
import com.google.android.gms.maps.internal.zzp;
import com.google.android.gms.maps.internal.zzr;
import com.google.android.gms.maps.internal.zzv;
import com.google.android.gms.maps.internal.zzx;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class MainActivityTest{

    private final SharedPreferences sharedPreferences = new SharedPreferences() {
        @Override
        public Map<String, ?> getAll() {
            return null;
        }

        @Nullable
        @Override
        public String getString(String key, @Nullable String defValue) {
            return null;
        }

        @Nullable
        @Override
        public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
            return null;
        }

        @Override
        public int getInt(String key, int defValue) {
            return 0;
        }

        @Override
        public long getLong(String key, long defValue) {
            return 0;
        }

        @Override
        public float getFloat(String key, float defValue) {
            return 0;
        }

        @Override
        public boolean getBoolean(String key, boolean defValue) {
            return false;
        }

        @Override
        public boolean contains(String key) {
            return false;
        }

        @Override
        public Editor edit() {
            return null;
        }

        @Override
        public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        }

        @Override
        public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        }
    };

    private final Marker marker = new Marker(new zzt() {
        @Override
        public void remove() throws RemoteException {

        }

        @Override
        public String getId() throws RemoteException {
            return null;
        }

        @Override
        public void setPosition(LatLng latLng) throws RemoteException {

        }

        @Override
        public LatLng getPosition() throws RemoteException {
            return null;
        }

        @Override
        public void setTitle(String s) throws RemoteException {

        }

        @Override
        public String getTitle() throws RemoteException {
            return null;
        }

        @Override
        public void setSnippet(String s) throws RemoteException {

        }

        @Override
        public String getSnippet() throws RemoteException {
            return null;
        }

        @Override
        public void setDraggable(boolean b) throws RemoteException {

        }

        @Override
        public boolean isDraggable() throws RemoteException {
            return false;
        }

        @Override
        public void showInfoWindow() throws RemoteException {

        }

        @Override
        public void hideInfoWindow() throws RemoteException {

        }

        @Override
        public boolean isInfoWindowShown() throws RemoteException {
            return false;
        }

        @Override
        public void setVisible(boolean b) throws RemoteException {

        }

        @Override
        public boolean isVisible() throws RemoteException {
            return false;
        }

        @Override
        public boolean zzj(zzt zzt) throws RemoteException {
            return false;
        }

        @Override
        public int zzj() throws RemoteException {
            return 0;
        }

        @Override
        public void zzg(IObjectWrapper iObjectWrapper) throws RemoteException {

        }

        @Override
        public void setAnchor(float v, float v1) throws RemoteException {

        }

        @Override
        public void setFlat(boolean b) throws RemoteException {

        }

        @Override
        public boolean isFlat() throws RemoteException {
            return false;
        }

        @Override
        public void setRotation(float v) throws RemoteException {

        }

        @Override
        public float getRotation() throws RemoteException {
            return 0;
        }

        @Override
        public void setInfoWindowAnchor(float v, float v1) throws RemoteException {

        }

        @Override
        public void setAlpha(float v) throws RemoteException {

        }

        @Override
        public float getAlpha() throws RemoteException {
            return 0;
        }

        @Override
        public void setZIndex(float v) throws RemoteException {

        }

        @Override
        public float getZIndex() throws RemoteException {
            return 0;
        }

        @Override
        public void zze(IObjectWrapper iObjectWrapper) throws RemoteException {

        }

        @Override
        public IObjectWrapper zzk() throws RemoteException {
            return null;
        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    });

    private final GoogleMap googleMap = new GoogleMap(new IGoogleMapDelegate() {
        @Override
        public CameraPosition getCameraPosition() throws RemoteException {
            return null;
        }

        @Override
        public float getMaxZoomLevel() throws RemoteException {
            return 0;
        }

        @Override
        public float getMinZoomLevel() throws RemoteException {
            return 0;
        }

        @Override
        public void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {

        }

        @Override
        public void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {

        }

        @Override
        public void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzc zzc) throws RemoteException {

        }

        @Override
        public void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzc zzc) throws RemoteException {

        }

        @Override
        public void stopAnimation() throws RemoteException {

        }

        @Override
        public zzz addPolyline(PolylineOptions polylineOptions) throws RemoteException {
            return null;
        }

        @Override
        public zzw addPolygon(PolygonOptions polygonOptions) throws RemoteException {
            return null;
        }

        @Override
        public zzt addMarker(MarkerOptions markerOptions) throws RemoteException {
            return null;
        }

        @Override
        public zzk addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
            return null;
        }

        @Override
        public zzac addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
            return null;
        }

        @Override
        public void clear() throws RemoteException {

        }

        @Override
        public int getMapType() throws RemoteException {
            return 0;
        }

        @Override
        public void setMapType(int i) throws RemoteException {

        }

        @Override
        public boolean isTrafficEnabled() throws RemoteException {
            return false;
        }

        @Override
        public void setTrafficEnabled(boolean b) throws RemoteException {

        }

        @Override
        public boolean isIndoorEnabled() throws RemoteException {
            return false;
        }

        @Override
        public boolean setIndoorEnabled(boolean b) throws RemoteException {
            return false;
        }

        @Override
        public boolean isMyLocationEnabled() throws RemoteException {
            return false;
        }

        @Override
        public void setMyLocationEnabled(boolean b) throws RemoteException {

        }

        @Override
        public Location getMyLocation() throws RemoteException {
            return null;
        }

        @Override
        public void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {

        }

        @Override
        public IUiSettingsDelegate getUiSettings() throws RemoteException {
            return null;
        }

        @Override
        public IProjectionDelegate getProjection() throws RemoteException {
            return null;
        }

        @Override
        public void setOnCameraChangeListener(zzl zzl) throws RemoteException {

        }

        @Override
        public void setOnMapClickListener(zzaj zzaj) throws RemoteException {

        }

        @Override
        public void setOnMapLongClickListener(zzan zzan) throws RemoteException {

        }

        @Override
        public void setOnMarkerClickListener(zzar zzar) throws RemoteException {

        }

        @Override
        public void setOnMarkerDragListener(zzat zzat) throws RemoteException {

        }

        @Override
        public void setOnInfoWindowClickListener(zzab zzab) throws RemoteException {

        }

        @Override
        public void setInfoWindowAdapter(zzh zzh) throws RemoteException {

        }

        @Override
        public com.google.android.gms.internal.maps.zzh addCircle(CircleOptions circleOptions) throws RemoteException {
            return null;
        }

        @Override
        public void setOnMyLocationChangeListener(zzax zzax) throws RemoteException {

        }

        @Override
        public void setOnMyLocationButtonClickListener(zzav zzav) throws RemoteException {

        }

        @Override
        public void snapshot(zzbs zzbs, IObjectWrapper iObjectWrapper) throws RemoteException {

        }

        @Override
        public void setPadding(int i, int i1, int i2, int i3) throws RemoteException {

        }

        @Override
        public boolean isBuildingsEnabled() throws RemoteException {
            return false;
        }

        @Override
        public void setBuildingsEnabled(boolean b) throws RemoteException {

        }

        @Override
        public void setOnMapLoadedCallback(zzal zzal) throws RemoteException {

        }

        @Override
        public zzn getFocusedBuilding() throws RemoteException {
            return null;
        }

        @Override
        public void setOnIndoorStateChangeListener(com.google.android.gms.maps.internal.zzz zzz) throws RemoteException {

        }

        @Override
        public void setWatermarkEnabled(boolean b) throws RemoteException {

        }

        @Override
        public void getMapAsync(zzap zzap) throws RemoteException {

        }

        @Override
        public void onCreate(Bundle bundle) throws RemoteException {

        }

        @Override
        public void onResume() throws RemoteException {

        }

        @Override
        public void onPause() throws RemoteException {

        }

        @Override
        public void onDestroy() throws RemoteException {

        }

        @Override
        public void onLowMemory() throws RemoteException {

        }

        @Override
        public boolean useViewLifecycleWhenInFragment() throws RemoteException {
            return false;
        }

        @Override
        public void onSaveInstanceState(Bundle bundle) throws RemoteException {

        }

        @Override
        public void setContentDescription(String s) throws RemoteException {

        }

        @Override
        public void snapshotForTest(zzbs zzbs) throws RemoteException {

        }

        @Override
        public void setOnPoiClickListener(zzbb zzbb) throws RemoteException {

        }

        @Override
        public void onEnterAmbient(Bundle bundle) throws RemoteException {

        }

        @Override
        public void onExitAmbient() throws RemoteException {

        }

        @Override
        public void setOnGroundOverlayClickListener(zzx zzx) throws RemoteException {

        }

        @Override
        public void setOnInfoWindowLongClickListener(zzaf zzaf) throws RemoteException {

        }

        @Override
        public void setOnPolygonClickListener(zzbd zzbd) throws RemoteException {

        }

        @Override
        public void setOnInfoWindowCloseListener(zzad zzad) throws RemoteException {

        }

        @Override
        public void setOnPolylineClickListener(zzbf zzbf) throws RemoteException {

        }

        @Override
        public void setOnCircleClickListener(zzv zzv) throws RemoteException {

        }

        @Override
        public void setMinZoomPreference(float v) throws RemoteException {

        }

        @Override
        public void setMaxZoomPreference(float v) throws RemoteException {

        }

        @Override
        public void resetMinMaxZoomPreference() throws RemoteException {

        }

        @Override
        public void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {

        }

        @Override
        public void setOnCameraMoveStartedListener(com.google.android.gms.maps.internal.zzt zzt) throws RemoteException {

        }

        @Override
        public void setOnCameraMoveListener(zzr zzr) throws RemoteException {

        }

        @Override
        public void setOnCameraMoveCanceledListener(zzp zzp) throws RemoteException {

        }

        @Override
        public void setOnCameraIdleListener(com.google.android.gms.maps.internal.zzn zzn) throws RemoteException {

        }

        @Override
        public boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
            return false;
        }

        @Override
        public void onStart() throws RemoteException {

        }

        @Override
        public void onStop() throws RemoteException {

        }

        @Override
        public void setOnMyLocationClickListener(zzaz zzaz) throws RemoteException {

        }

        @Override
        public IBinder asBinder() {
            return null;
        }
    });

    @Test
    public void updateTask() {
        MainActivity mainActivity = new MainActivity();
        mainActivity.setLatLng(new LatLng(0, 0));
        mainActivity.setMarker(marker);
        mainActivity.setGoogleMap(googleMap);
        mainActivity.updateTask(null);
    }

    @Test
    public void testGetCurrentScore() {
        MainActivity mainActivity = new MainActivity();
        mainActivity.setSharedPreferences(sharedPreferences);
        mainActivity.getCurrentScore();
    }
}